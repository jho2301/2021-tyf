import RegisterAddressForm from '../../../components/Register/AddressForm/RegisterAddressForm';
import usePageRefreshGuardEffect from '../../../utils/usePageRefreshGuardEffect';
import { REGISTER_PAGE_KEY } from '../Auth/RegisterAuthPage';
import { StyledTemplate } from './RegisterAddressPage.styles';

const RegisterAddressPage = () => {
  usePageRefreshGuardEffect(REGISTER_PAGE_KEY, false, '/register');

  return (
    <StyledTemplate>
      <RegisterAddressForm />
    </StyledTemplate>
  );
};

export default RegisterAddressPage;
