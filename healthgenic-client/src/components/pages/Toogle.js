import React from "react";
import "./formSign/Form.css";

const Toogle = ({ isOn, handleToggle, onColor }) => {
  return (
    <div className="switch-la">
      <div className="text-la">Patient</div>
      <input
        checked={isOn}
        onChange={handleToggle}
        className="react-switch-checkbox"
        id={`react-switch-new`}
        type="checkbox"
      />
      <label
        style={{ background: isOn && onColor }}
        className="react-switch-label"
        htmlFor={`react-switch-new`}
      >
        <span className={`react-switch-button`}></span>
      </label>
      <div className="text-la">Doctor </div>
    </div>
  );
};

export default Toogle;
