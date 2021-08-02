import { useRecoilState, useRecoilValue } from 'recoil';
import { STORAGE_KEY } from '../../constants/storage';
import { setLocalStorageItem, setSessionStorageItem } from '../../utils/storage';
import { accessTokenState, loginPersistenceTypeState } from '../state/login';

const useAccessToken = () => {
  const [accessToken, setAccessToken] = useRecoilState(accessTokenState);
  const loginPersistenceType = useRecoilValue(loginPersistenceTypeState);

  const clearAccessToken = () => {
    localStorage.removeItem(STORAGE_KEY.ACCESS_TOKEN);
    sessionStorage.removeItem(STORAGE_KEY.ACCESS_TOKEN);
    setAccessToken('');
  };

  const storeAccessToken = (accessToken: string) => {
    clearAccessToken();

    loginPersistenceType === 'LOCAL'
      ? setLocalStorageItem(STORAGE_KEY.ACCESS_TOKEN, accessToken)
      : setSessionStorageItem(STORAGE_KEY.ACCESS_TOKEN, accessToken);

    setAccessToken(accessToken);
  };

  return { accessToken, storeAccessToken, clearAccessToken };
};

export default useAccessToken;
