import styled from "styled-components";

export const StyledLivro = styled.div`
    display: block;
    border: 5px;
    border-color: black;
    width: 15.74vw;
    height: 270px;
    font-size: 12px;
    background-color: gray;
    margin:2px;
    align-items: center;
    @media (max-width:450px){
        width:86px;
        height: 108px;

    }

 
`

export const StyledImage = styled.img`
    width:100%;
    height:100%
`