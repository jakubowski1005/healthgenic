import React, { useState } from "react";
import { getMessages, sendMessage } from "../../services/MessageService";
import { getUserInfo } from "../../services/UserService";
import "./Compose.css";

export default function Compose({ PATIENT_ID, date }) {
  const [state, setState] = useState({ message: "" });
  const [stateId, setStateId] = useState({ id: "" });

  const messageData = {
    from: stateId.id,
    to: PATIENT_ID,
    sentAt: date,
  };

  const userIdGet = () => {
    getUserInfo()
      .then((response) => response.json())
      .then((data) => {
        setStateId({
          id: data.id,
        });
        console.log(data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const handleChange = (event) => {
    setState({ message: event.target.value });
    userIdGet();
  };

  const handleSubmit = (event) => {
    if (state.message === "") {
      event.preventDefault();
      return;
    }
    //setState({ message: event.target.value });
    event.preventDefault();
    messageData["content"] = state.message;
    sendMessage(messageData)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        getMessages()
          .then((response) => response.json())
          .then((data) => {
            console.log(data);
          })
          .catch((e) => {
            console.log(e);
          });
      })
      .catch((err) => console.error(err));
    console.log(messageData);
    setState({ message: "" });
  };

  return (
    <div className="compose">
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="message"
          className="compose-input"
          placeholder="Type a message..."
          value={state.message}
          onChange={handleChange}
        />
        <button type="submit" className="form-input-btn-c">
          Send
        </button>
      </form>
    </div>
  );
}
