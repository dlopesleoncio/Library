
import { StyledLivro,StyledImage } from "./styles/Livro.Styled"
export default function Livro({livro}){
    
    console.log(livro)
    const livroPath = livro.img_path
    
    return(
        
            <StyledLivro>
                <StyledImage src={livroPath}/>
            </StyledLivro>

    )
        

    


}