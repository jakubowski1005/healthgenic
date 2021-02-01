import React from "react";
import "./Message.css";
import patientData from "../jsons/patientList.json";
import MessageForm from "./MessageForm";
import NavbarD from "../NavbarD";

export const Message = () => {
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
          <MessageForm />
        </div>
      </div>
    </>
  );
};

const Patients = ({ patientid, firstName, lastName }) => {
  if (!firstName) return <div />;
  return (
    <>
      <table id="table-names">
        <tbody>
          <tr data-user1={patientid}>
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

document.addEventListener("DOMContentLoaded", () => {
  const rows = document.querySelectorAll("tr[data-user1");
  console.log(rows);
  let p_id = "";

  rows.forEach((row) => {
    row.addEventListener("click", () => {
      console.log(row.dataset.user1);
      p_id = row.dataset.user1;
    });
  });
});

export default Message;
