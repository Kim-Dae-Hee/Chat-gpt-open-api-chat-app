import React from "react";
import { FaSun, FaMoon } from "react-icons/fa6";
import { useDarkMode } from "../../context/DarkModeContext";
import style from './Header.module.css';

export default function Header() {
  const { darkMode, toggleDarkMode } = useDarkMode();

  return (
    <header className={style['header']}>
      <p className={style['header-paragraph']}>당신의 궁금한 점은?</p>
      <button className={style['header-button']} onClick={toggleDarkMode}>
        {!darkMode && <FaSun />}
        {darkMode && <FaMoon />}
      </button>
    </header>
  )
}