import { selectToken } from 'modules/session/sessionSelectors';
import { ContestClarificationData, ContestClarificationStatus } from 'modules/api/uriel/contestClarification';

export const contestClarificationActions = {
  createClarification: (contestJid: string, data: ContestClarificationData) => {
    return async (dispatch, getState, { contestClarificationAPI, toastActions }) => {
      const token = selectToken(getState());
      await contestClarificationAPI.createClarification(token, contestJid, data);
      toastActions.showSuccessToast('Clarification submitted.');
    };
  },

  getClarifications: (contestJid: string, language?: string, page?: number) => {
    return async (dispatch, getState, { contestClarificationAPI }) => {
      const token = selectToken(getState());
      return await contestClarificationAPI.getClarifications(token, contestJid, language, page);
    };
  },

  answerClarification: (contestJid: string, clarificationJid: string, answer: string) => {
    return async (dispatch, getState, { contestClarificationAPI }) => {
      const token = selectToken(getState());
      await contestClarificationAPI.answerClarification(token, contestJid, clarificationJid, { answer });
    };
  },

  alertNewClarifications: (status: ContestClarificationStatus) => {
    return async (dispatch, getState, { toastActions }) => {
      if (status === ContestClarificationStatus.Answered) {
        toastActions.showAlertToast('You have new answered clarification(s).');
      } else {
        toastActions.showAlertToast('You have new clarification(s).');
      }
    };
  },
};
