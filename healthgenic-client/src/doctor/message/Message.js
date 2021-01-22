import React, { useState } from "react";

import MessageForm from "./MessageForm";
import NavbarD from "../NavbarD";

import patientData from "../jsons/patientList.json";
import "./Message.css";

export function Message() {
  const [state, setState] = useState("");

  const Patients = ({ patientid, firstName, lastName }) => {
    if (!firstName) return <div />;
    return (
      <>
        <table id="table-names">
          <tbody>
            <tr onClick={() => setState(patientid)}>
              <td>
                <h5>
                  {firstName} {lastName}
                </h5>
              </td>
            </tr>
          </tbody>
        </table>
      </>
    );
  };

  return (
    <>
      <NavbarD />
      <div className="form-container-m">
        <div className="form-content-left-l">
          {patientData.map((data, key) => {
            return (
              <div key={key}>
                <Patients
                  key={key}
                  patientid={data.id}
                  firstName={data.firstName}
                  lastName={data.lastName}
                />
              </div>
            );
          })}
        </div>

        <div className="form-content-right-m">
          <MessageForm PATIENT_USER_ID={state} />
        </div>
      </div>
    </>
  );
}

export default Message;
