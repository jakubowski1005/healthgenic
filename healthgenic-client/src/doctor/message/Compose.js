import React, { useState } from "react";
import { getMessages, sendMessage } from "../../services/MessageService";
import "./Compose.css";

export default function Compose({ USER_ID, PATIENT_ID, date }) {
  const [state, setState] = useState({ message: "" });

  const messageData = {
    from: USER_ID,
    to: PATIENT_ID,
    sentAt: date,
  };

  const handleChange = (event) => {
    setState({ message: event.target.value });
  };

  const handleSubmit = (event) => {
    if (state.message === "") {
      event.preventDefault();
      return;
    }
    //setState({ message: event.target.value });
    console.log(state.message);
    messageData["content"] = state.message;
    console.log(messageData);
    sendMessage(messageData);
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
