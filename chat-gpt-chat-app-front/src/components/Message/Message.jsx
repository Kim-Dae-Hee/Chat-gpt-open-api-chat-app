import React from "react";
import style from './Message.module.css';

export default function Message({ message }) {
  const { text, sender, createdDate } = message;
  const formattedDate = new Date(createdDate).toLocaleString();
  return (
    <li className={style["message"]}>
      <div className={sender === "user" ? style['user-message'] : style['bot-message']}>{text}</div>
      <div className={sender === "user" ? style['user-date'] : style['bot-date']}>{formattedDate}</div>
    </li>
  );
}