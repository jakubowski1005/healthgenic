import React, { useState } from "react";
import "./Compose.css";

export default function Compose(props) {
  const [state, setState] = useState({ message: "" });

  const handleChange = (event) => {
    setState({ message: event.target.value });
  };

  const handleSubmit = (event) => {
    console.log(state.message);
    event.preventDefault(); //to usunąć jak zapisze się do jsona
    setState({ message: event.target.value });
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
      {props.rightItems}
    </div>
  );
}
