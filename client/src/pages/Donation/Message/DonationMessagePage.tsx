import { useHistory, useParams } from 'react-router-dom';

import { ParamTypes } from '../../../App';
import TextButton from '../../../components/@atom/TextButton/TextButton.styles';
import { FixedLogo } from '../../../components/@molecule/Logo/Logo';
import MessageForm from '../../../components/Donation/MessageForm/DonationMessageForm';
import { popupWindow } from '../../../service/popup';
import { StyledTemplate } from './DonationMessagePage.styles';

const DonationMessagePage = () => {
  const { creatorId } = useParams<ParamTypes>();
  const history = useHistory();

  return (
    <StyledTemplate>
      <FixedLogo onClick={() => popupWindow('/')} />
      <section>
        <MessageForm creatorId={creatorId} />
      </section>
      <TextButton onClick={() => history.push(`/donation/${creatorId}/success`)}>
        넘어가기
      </TextButton>
    </StyledTemplate>
  );
};

export default DonationMessagePage;
