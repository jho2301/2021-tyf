import { FormEvent } from 'react';

import useDonation from '../../../service/hooks/useDonation';
import useDonationForm from '../../../service/hooks/useDonationForm';
import { CreatorId } from '../../../types';
import { toCommaSeparatedString } from '../../../utils/format';
import Button from '../../@atom/Button/Button';
import SubTitle from '../../@atom/SubTitle/SubTitle';
import {
  ButtonContainer,
  InputLabel,
  MoneyAddButton,
  MoneyInput,
  StyledDonationForm,
} from './DonationForm.styles';

export interface DonationFormProps {
  creatorId: CreatorId;
}

const DonationForm = ({ creatorId }: DonationFormProps) => {
  const { donationAmount, addDonationAmount, setDonationAmount, isDonationAmountInValidRange } =
    useDonationForm();
  const { donate } = useDonation(creatorId);

  const onDonate = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    donate(Number(donationAmount));
  };

  return (
    <StyledDonationForm onSubmit={onDonate}>
      <SubTitle>후원할 금액을 입력해주세요! 🎉</SubTitle>
      <InputLabel>
        <MoneyInput
          placeholder="0"
          value={donationAmount}
          onChange={({ target }) => setDonationAmount(target.value)}
        />
      </InputLabel>
      <ButtonContainer>
        <MoneyAddButton onClick={() => addDonationAmount(1000)}>
          +{toCommaSeparatedString(1000)}원
        </MoneyAddButton>
        <MoneyAddButton onClick={() => addDonationAmount(2000)}>
          +{toCommaSeparatedString(2000)}원
        </MoneyAddButton>
        <MoneyAddButton onClick={() => addDonationAmount(3000)}>
          +{toCommaSeparatedString(3000)}원
        </MoneyAddButton>
      </ButtonContainer>
      <Button disabled={!isDonationAmountInValidRange}>후원하기</Button>
    </StyledDonationForm>
  );
};

export default DonationForm;