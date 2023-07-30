import React from "react";
import { useState, useRef, useEffect } from "react";
import style from "./Screen.module.css";
import Message from "../Message/Message";
import AddMessage from "../AddMessage/AddMessage";
import { http } from "../../utils/http";

export default function Screen() {
  const [messages, setMessages] = useState([]);
  const messageListRef = useRef(null);
  const url = "http://localhost:8080/api/messages";
  const saveUrl = "http://localhost:8080/api/messages/save";

  useEffect(() => {
    console.log('초기화 메시지 가져옴')
    fetchMessages();
  }, []); // 의존성 배열을 빈 배열로 하여 컴포넌트가 처음 마운트될 때만 실행

  const fetchMessages = async () => {
    try {
      const response = await http.get(url);
      setMessages(response);
      scrollToLatestMessage();
    } catch (error) {
      console.error("메시지 가져오기 실패:", error);
    }
  };

  const handleAdd = async (questionRequest) => {
    try {
      const responseRequest = await http.post(saveUrl, questionRequest);
      setMessages((prevMessages) => [...prevMessages, responseRequest]);
      console.log(`질문 메시지 ${JSON.stringify(responseRequest)}`);
      setTimeout(() => {
        scrollToLatestMessage();
      }, 10);
      
      const answer = await http.post(url, questionRequest);
      setMessages((prevMessages) => [...prevMessages, answer]);
      console.log(`응답 메시지 ${JSON.stringify(answer)}`);
      setTimeout(() => {
        scrollToLatestMessage();
      }, 10);

    } catch (error) {
      console.error("메시지 전송 실패:", error);
    }
  };

  const scrollToLatestMessage = () => {
    if (messageListRef.current) {
      const messageList = messageListRef.current;
      const latestMessage = messageList.lastElementChild;
      if (latestMessage) {
        latestMessage.scrollIntoView({ behavior: "smooth" });
      }
    }
  };

  return (
    <section className={style["chat-screen"]}>
      <ul className={style["message-list"]} ref={messageListRef}>
        {messages.map((message) => (
          <Message key={message.id} message={message} />
        ))}
      </ul>
      <AddMessage onAdd={handleAdd} />
    </section>
  );
}
