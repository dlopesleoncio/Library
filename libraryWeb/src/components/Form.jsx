import { useState } from 'react';

export default function Form(){
    const [formData, setFormData] = useState({
        titulo: '',
        autor: '',
        disponivel: 'true',
        anoPublicacao: ''
      });
    
      const handleInputChange = (event) => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
      };
    
      const handleSubmit = (event) => {
        event.preventDefault();
        //requisição POST usando os dados do formData
        fetch('http://localhost:8080/api/livros', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify( formData ),
        })
          .then(response => response.json())
          .then(data => {
            // Tratar a resposta da API
            console.log(data);
          })
          .catch(error => {
            // Tratar erros
            console.error('Erro:', error);
          });
    
        console.log(formData);
      };




    return(
        <form onSubmit={handleSubmit}>
        <label>
          Título:
          <input
            type="text"
            name="titulo"
            value={formData.titulo}
            onChange={handleInputChange}
          />
        </label>
        <label>
          Autor:
          <input
            type="text"
            name="autor"
            value={formData.autor}
            onChange={handleInputChange}
          />
        </label>
        <label>
          Ano de publicação:
          <input
            type="text"
            name="anoPublicacao"
            value={formData.anoPublicacao}
            onChange={handleInputChange}
          />
        </label>        
        
        <br />
        <button type="submit">Submit</button>
      </form>
        
    )


}