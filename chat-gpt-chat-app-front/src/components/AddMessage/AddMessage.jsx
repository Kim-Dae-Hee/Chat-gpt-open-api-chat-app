import React, { useState } from "react";
import style from './AddMessage.module.css';

export default function AddMessage({ onAdd }) {
  const [inputValue, setInputValue] = useState('');
  
  const handleInputChange = (e) => {
    setInputValue(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (inputValue.trim() !== '') {
      const requestMessage = {
        question: inputValue
      }
      onAdd(requestMessage);
      setInputValue('');
    }
  };

  return (
    <form className={style['input-form']} onSubmit={handleSubmit}>
    <input
      type="text"
      value={inputValue}
      onChange={handleInputChange}
      placeholder="질문을 입력하세요."
    />
    <button>Send</button>
  </form>
  );
}