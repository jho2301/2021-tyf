import axios, { AxiosInstance } from 'axios';

const baseURL = 'https://thank-you-for-test.kro.kr/';

export const apiClient: AxiosInstance = axios.create({
  baseURL,
  headers: {
    'Content-Type': 'application/json; charset=utf-8',
  },
});

apiClient.interceptors.response.use(
  (response) => response,
  (error) => error.response
);

// export const createAuthorizationHeader = () => {};
