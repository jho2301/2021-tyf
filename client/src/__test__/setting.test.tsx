import { screen, waitFor } from '@testing-library/dom';
import userEvent from '@testing-library/user-event';
import { userInfoMock } from '../mock/mockData';
import SettingPage from '../pages/Setting/SettingPage';
import { myRender } from './utils/testUtil';

describe('Setting', () => {
  beforeEach(() => {
    myRender(<SettingPage />);
  });

  test('기존의 프로필 이미지, 닉네임, 바이오가 입력창에 초기화된다.', async () => {
    waitFor(() => {
      const $nicknameInput = screen.getByRole('nickname-input') as HTMLInputElement;
      const $bioInput = screen.getByRole('bio-input') as HTMLInputElement;
      const $profileImg = screen.getByRole('profile-img') as HTMLImageElement;

      expect($nicknameInput.value).toBe(userInfoMock.nickname);
      expect($bioInput.value).toBe(userInfoMock.bio);
      expect($profileImg.src).toMatch(new RegExp(`${userInfoMock.profileImage}$`));
    });
  });

  test('유효한 값을 입력 후 저장하면 새로고침이 된다.', () => {
    const reloadSpy = jest.fn();
    const $nicknameInput = screen.getByRole('nickname-input') as HTMLInputElement;
    const $bioInput = screen.getByRole('bio-input') as HTMLInputElement;
    const $profileImgInput = screen.getByRole('profile-img-input', {
      hidden: true,
    }) as HTMLInputElement;
    const file = new File(['hello'], 'hello.png', { type: 'image/png' });
    const $applyButton = screen.getByRole('button', { name: '적용하기' });

    Object.defineProperty(window, 'location', { value: { reload: reloadSpy } });

    userEvent.type($nicknameInput, '나노');
    userEvent.type($bioInput, '바뀐 자기소개');
    userEvent.upload($profileImgInput, file);
    userEvent.click($applyButton);

    waitFor(() => {
      expect(reloadSpy).toHaveBeenCalled();
    });
  });
});
