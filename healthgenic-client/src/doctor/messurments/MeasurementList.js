import React, { Component } from "react";
//import TeacherDataService from "../../services/teacher.service";

import dataMesurment from "../jsons/dataMesurments.json";
import { getPatientsMeasurements } from "../../services/MeasurementService";
import "../List.css";

export default class MeasurementList extends Component {
  constructor(props) {
    super(props);
    this.onChangeSearchTypesearchType = this.onChangeSearchTypesearchType.bind(
      this
    );
    this.retrieveMeasurments = this.retrieveMeasurments.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActiveMeasurement = this.setActiveMeasurement.bind(this);
    this.searchType = this.searchType.bind(this);

    this.state = {
      measurments: [],
      currentMeasurement: null,
      currentIndex: -1,
      searchType: "",
    };
  }

  componentDidMount() {
    this.retrieveMeasurments();
  }

  onChangeSearchTypesearchType(e) {
    const searchType = e.target.value;
    this.retrieveMeasurments();
    this.setState({
      searchType: searchType,
    });
  }

  retrieveMeasurments() {
    getPatientsMeasurements(this.props.PATIENT_USER_ID)
      .then((response) => response.json())
      .then((data) => {
        this.setState({
          measurments: data,
        });
        console.log(data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  refreshList() {
    this.retrieveMeasurments();
    this.setState({
      currentMeasurement: null,
      currentIndex: -1,
    });
  }

  setActiveMeasurement(teacher, index) {
    this.setState({
      currentMeasurement: teacher,
      currentIndex: index,
    });
  }

  searchType() {
    this.setState({
      currentMeasurement: null,
      currentIndex: -1,
    });
  }

  render() {
    console.log(this.props.PATIENT_USER_ID);
    // this.retrieveMeasurments();
    const {
      searchType,
      measurments,
      currentMeasurement,
      currentIndex,
    } = this.state;

    // const inputchangehandler = (event) => {
    //   setUserInput(event.target.value);
    // };

    const _getUniqueCampusName = () => {
      const typeSelection = [...new Set(measurments.map((item) => item.type))];
      return typeSelection.map((type, index) => (
        <option className="selectCustom-options" key={index}>
          {type}
        </option>
      ));
    };

    const types = _getUniqueCampusName();

    const Measurements = ({ ownerId, type, date, value, unit, input }) => {
      if (ownerId !== this.props.PATIENT_USER_ID) return <div />;
      if (input !== type) return <div />;
      return (
        <table id="table-measurments">
          <tbody>
            <tr>
              <td>
                <h4>
                  {date.substr(0, 10)} {date.substr(11, 5)}
                </h4>
              </td>
              <td>
                <h4>{value.substr(0, 5)}</h4>
              </td>
              <td>
                <h4>{unit}</h4>
              </td>
            </tr>
          </tbody>
        </table>
      );
    };

    return (
      <>
        <select
          className="select-css"
          value={searchType}
          onChange={this.onChangeSearchTypesearchType}
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

          {measurments.map((data, key) => {
            return (
              <div key={key}>
                <Measurements
                  key={key}
                  ownerId={data.ownerId}
                  type={data.type}
                  date={data.createdAt}
                  value={data.value}
                  unit={data.unit}
                  input={searchType}
                />
              </div>
            );
          })}
        </div>
      </>
    );
  }
}

/*
import React, { useState } from "react";
import dataMesurment from "../jsons/dataMesurments.json";
import { getPatientsMeasurements } from "../../services/MeasurementService";
import "../List.css";

function MeasurementList(props) {
  const { PATIENT_USER_ID } = props;
  const [userInput, setUserInput] = useState("");
  const [measuremtns, setMeasurements] = useState([]);

  getPatientsMeasurements(PATIENT_USER_ID)
    .then((response) => response.json())
    .then((data) => console.log(data))
    .catch((err) => console.error(err));

  // getPatientsMeasurements()
  //   .then((response) => response.json())
  //   .then((data) => {
  //     this.setMeasurements({
  //       measuremtns: data,
  //     });
  //     console.log(measuremtns);
  //   })
  //   .catch((e) => {
  //     console.log(e);
  //   });

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

  const Measurements = ({ ownerId, type, createdAt, value, unit, input }) => {
    if (ownerId !== PATIENT_USER_ID) return <div />;
    if (input !== type) return <div />;
    return (
      <table id="table-measurments">
        <tbody>
          <tr>
            <td>
              <h4>{createdAt}</h4>
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
                date={data.createdAt}
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

export default MeasurementList;

*/
