import styled from 'styled-components';

import PALETTE from '../../../../constants/palette';
import Button from '../../../@atom/Button/Button';
import Container from '../../../@atom/Container/Container.styles';

export const StyledCreatorInfo = styled.section`
  margin-top: 4rem;
  margin-bottom: 10rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const DescriptionContainer = styled(Container)`
  margin: 3rem 0 5rem 0;
  border-top: 1px solid ${({ theme }) => theme.color.border};
  border-bottom: 1px solid ${({ theme }) => theme.color.border};
  padding: 1rem;
  color: ${PALETTE.GRAY_500};
  min-height: 6rem;
`;

export const StyledButton = styled(Button)``;
