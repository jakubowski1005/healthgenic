import React, { useState } from "react";
import dataMesurment from "../jsons/dataMesurments.json";

function MeasurementList({ patientId }) {
  const [userInput, setUserInput] = useState("");
  const inputchangehandler = (event) => {
    setUserInput(event.target.value);
  };

  const _getUniqueCampusName = () => {
    const typeSelection = [...new Set(dataMesurment.map((item) => item.type))];
    return typeSelection.map((type, index) => (
      <option className="selectCustom-options" key={index}>
        {type}
      </option>
    ));
  };

  const types = _getUniqueCampusName();

  return (
    <>
      <select
        className="select-css"
        value={userInput}
        onChange={inputchangehandler}
      >
        <option disabled={true} value="">
          Select type
        </option>
        {types}
      </select>
      <div>
        <table id="table-measurments">
          <tbody>
            <tr>
              <td>
                <h4>date</h4>
              </td>
              <td>
                <h4>value</h4>
              </td>
              <td>
                <h4>unit</h4>
              </td>
            </tr>
          </tbody>
        </table>

        {dataMesurment.map((data, key) => {
          return (
            <div key={key}>
              <Measurements
                key={key}
                ownerId={data.ownerId}
                type={data.type}
                date={data.date}
                value={data.value}
                unit={data.unit}
                input={userInput}
              />
            </div>
          );
        })}
      </div>
    </>
  );
}

const Measurements = ({ ownerId, type, date, value, unit, input }) => {
  if (!ownerId) return <div />;
  if (input !== type) return <div />;
  return (
    <table id="table-measurments">
      <tbody>
        <tr>
          <td>
            <h4>{date}</h4>
          </td>
          <td>
            <h4>{value}</h4>
          </td>
          <td>
            <h4>{unit}</h4>
          </td>
        </tr>
      </tbody>
    </table>
  );
};
export default MeasurementList;
