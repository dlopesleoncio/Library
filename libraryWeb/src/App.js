import React from 'react';
import './App.css';
import { useState,useEffect } from 'react';
import Form from './components/Form';
import Livro from './components/Livro';
import { StyledNav,StyledInput } from './components/styles/Nav.Styled';
import DropdownNav from './components/Dropdown';
function App() {
  
  const [categoria,setCategoria] = useState('');
  const [livros, setLivros] = useState([]);
  const [queryText,setQuery] = useState('');
  
  

  useEffect(() => {
    const fetchData = async () => {
      try {
        //const params = {
        //  query: queryText,
//};
       // const queryString = new URLSearchParams(params).toString();
        const response = await fetch(`http://localhost:8080/api/livros/nomes?titulo=${queryText}`);
        const data = await response.json();
        setLivros(data);
      } catch (error) {
        console.error(error);
      }
    };
    if(queryText){
      fetchData();
    }
    
  }, [queryText]);

useEffect(() => {
  const fetchData = async () => {
    try {
      //const params = {
      //  query: queryText,
//};
     // const queryString = new URLSearchParams(params).toString();
      const response = await fetch(`http://localhost:8080/api/livros/categoria/${categoria}`);
      const data = await response.json();
      setLivros(data);
    } catch (error) {
      console.error(error);
    }
  };
  if(categoria){
    fetchData();
  }
  
}, [categoria]);


  const handleInputChangeQuery = (event) => {
    setQuery(event.target.value);

    
  };
  const handleInputChangeDropDown  = (eventKey) => {
    setCategoria(eventKey);
  }

  return (
    <div className="App">
      <StyledNav>
        <h3 className='profile'>Profile</h3>
        <StyledInput type="text" value={queryText} onChange={handleInputChangeQuery} placeholder="Search by name"></StyledInput>
      </StyledNav>

      {/*
      <StyledNav>
        <h3 className='profile'>Profile</h3>
        <StyledInput type="text" value={queryText} onChange={handleInputChange} placeholder="Search by name"></StyledInput>
      </StyledNav>      
      
      */}
      <div className="dropdown"><DropdownNav categoria={categoria} setCategoria={handleInputChangeDropDown}/></div>
      

      <div>
        <div className="card-container">
          {
            livros.map((livro,index) => {return <Livro key={livro.id} livro = {livro}/>}) 
          }
        </div>
      </div>
      
    </div>
  );
}

export default App;
