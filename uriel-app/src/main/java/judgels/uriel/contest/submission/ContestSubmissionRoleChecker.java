package judgels.uriel.contest.submission;

import static judgels.uriel.api.contest.supervisor.SupervisorPermissionType.SUBMISSION;

import java.util.Optional;
import javax.inject.Inject;
import judgels.uriel.api.contest.Contest;
import judgels.uriel.api.contest.supervisor.ContestSupervisor;
import judgels.uriel.contest.ContestTimer;
import judgels.uriel.contest.supervisor.ContestSupervisorStore;
import judgels.uriel.persistence.AdminRoleDao;
import judgels.uriel.persistence.ContestRoleDao;

public class ContestSubmissionRoleChecker {
    private final AdminRoleDao adminRoleDao;
    private final ContestRoleDao contestRoleDao;
    private final ContestTimer contestTimer;
    private final ContestSupervisorStore supervisorStore;

    @Inject
    public ContestSubmissionRoleChecker(
            AdminRoleDao adminRoleDao,
            ContestRoleDao contestRoleDao,
            ContestTimer contestTimer,
            ContestSupervisorStore supervisorStore) {

        this.adminRoleDao = adminRoleDao;
        this.contestTimer = contestTimer;
        this.contestRoleDao = contestRoleDao;
        this.supervisorStore = supervisorStore;
    }

    public boolean canViewSubmission(String userJid, Contest contest, String submissionUserJid) {
        return userJid.equals(submissionUserJid) || isSupervisorWithSubmissionPermissionOrAbove(userJid, contest);
    }

    public boolean canViewOwnSubmissions(String userJid, Contest contest) {
        if (canSuperviseSubmissions(userJid, contest)) {
            return true;
        }
        return contestRoleDao.isContestantOrAbove(userJid, contest.getJid())
                && contestTimer.hasStarted(contest, userJid);
    }

    public boolean canSuperviseSubmissions(String userJid, Contest contest) {
        return isSupervisorWithSubmissionPermissionOrAbove(userJid, contest);
    }

    private boolean isSupervisorWithSubmissionPermissionOrAbove(String userJid, Contest contest) {
        if (adminRoleDao.isAdmin(userJid) || contestRoleDao.isManager(userJid, contest.getJid())) {
            return true;
        }
        Optional<ContestSupervisor> supervisor = supervisorStore.findSupervisor(contest.getJid(), userJid);
        return supervisor.isPresent() && supervisor.get().getPermission().allows(SUBMISSION);
    }
}
