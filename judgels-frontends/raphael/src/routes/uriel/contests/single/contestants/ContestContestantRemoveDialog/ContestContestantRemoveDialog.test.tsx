import { mount, ReactWrapper } from 'enzyme';
import * as React from 'react';
import { Provider } from 'react-redux';
import { MemoryRouter } from 'react-router';
import { combineReducers, createStore } from 'redux';
import { reducer as formReducer } from 'redux-form';

import { contest, contestJid } from 'fixtures/state';

import { ContestContestantRemoveDialog, ContestContestantRemoveDialogProps } from './ContestContestantRemoveDialog';

describe('ContestContestantRemoveDialog', () => {
  let onDeleteContestants: jest.Mock<any>;
  let wrapper: ReactWrapper<any, any>;

  beforeEach(() => {
    onDeleteContestants = jest.fn().mockReturnValue(() => Promise.resolve({}));

    const store = createStore(combineReducers({ form: formReducer }));

    const props: ContestContestantRemoveDialogProps = {
      contest,
      onDeleteContestants,
    };
    wrapper = mount(
      <Provider store={store}>
        <MemoryRouter>
          <ContestContestantRemoveDialog {...props} />
        </MemoryRouter>
      </Provider>
    );
  });

  test('remove contestants dialog form', () => {
    const button = wrapper.find('button');
    button.simulate('click');

    wrapper.update();

    const usernames = wrapper.find('textarea[name="usernames"]');
    usernames.simulate('change', { target: { value: 'andi\n\nbudi\n caca  \n' } });

    const form = wrapper.find('form');
    form.simulate('submit');

    expect(onDeleteContestants).toHaveBeenCalledWith(contestJid, ['andi', 'budi', 'caca']);
  });
});
