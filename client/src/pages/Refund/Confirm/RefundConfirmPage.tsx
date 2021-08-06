import Button from '../../../components/@atom/Button/Button.styles';
import TextButton from '../../../components/@atom/TextButton/TextButton.styles';
import RefundInfo from '../../../components/Refund/RefundInfo/RefundInfo';
import useRefund from '../../../service/hooks/refund/useRefund';
import usePageRefreshGuardEffect from '../../../utils/usePageRefreshGuardEffect';
import { REFUND_PAGE_KEY } from '../Apply/RefundApplyPage';
import { RefundConfirmPageTemplate, ButtonContainer } from './RefundConfirmPage.styles';

const RefundConfirmPage = () => {
  const { refundInfo, refund } = useRefund();

  usePageRefreshGuardEffect(REFUND_PAGE_KEY, false, '/refund');

  return (
    <RefundConfirmPageTemplate>
      <RefundInfo refundAccessToken={refundInfo.refundAccessToken} />
      <ButtonContainer>
        <Button onClick={refund}>환불 신청하기</Button>
        <TextButton>나가기</TextButton>
      </ButtonContainer>
    </RefundConfirmPageTemplate>
  );
};

export default RefundConfirmPage;
