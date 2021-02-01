import React from "react";
import "./Cards.css";
import CardItem from "./CardItem";

function Cards() {
  return (
    <div className="cards">
      <h1>A Great Place to Work! A Great Place to Receive Care!</h1>
      <div className="cards__container">
        <div className="cards__wrapper">
          <ul className="cards__items">
            <CardItem
              src="images/img-9.jpg"
              text="Screen panelu pacjenta"
              label="Patient"
              path="/sign-up"
            />
            <CardItem
              src="images/img-2.jpg"
              text="Screen panelu pacjenta"
              label="Patient"
              path="/sign-up"
            />
          </ul>
          <ul className="cards__items">
            <CardItem
              src="images/img-3.jpg"
              text="Screen panelu lekarza"
              label="Doctor"
              path="/sign-up"
            />
            <CardItem
              src="images/img-4.jpg"
              text="Screen panelu lekarza"
              label="Doctor"
              path="/sign-up"
            />
            <CardItem
              src="images/img-8.jpg"
              text="Screen panelu lekarza"
              label="Doctor"
              path="/sign-up"
            />
          </ul>
        </div>
      </div>
    </div>
  );
}

export default Cards;
