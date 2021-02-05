import React, { useEffect, useState } from "react";
import moment from "moment";
import Compose from "./Compose";
import Message from "./Mess";
import dataMessage from "../jsons/dataMessage.json";
import { getMessages, sendMessage } from "../../services/MessageService";
import "./MessageList.css";

//const MY_USER_ID = "apple";

export default function MessageForm(props) {
  const { PATIENT_USER_ID } = props;
  const [messages, setMessages] = useState([]);

  useEffect(() => {
    getMessagess();
  }, []);

  const getMessagess = () => {
    var tempMessages = dataMessage; //getMessages(); //!!!dataMessage||  pobieranie wiadomości z serwisu
    getMessages()
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
      })
      .catch((e) => {
        console.log(e);
      });
    setMessages([...messages, ...tempMessages]);
  };

  const renderMessages = () => {
    let i = 0;
    let messageCount = messages.length;
    let tempMessages = [];

    while (i < messageCount) {
      let previous = messages[i - 1];
      let current = messages[i];
      let next = messages[i + 1];
      let isMine =
        // current.from === MY_USER_ID &&
        current.to === PATIENT_USER_ID;
      let currentMoment = moment(current.sentAt);
      let prevBySameAuthor = false;
      let nextBySameAuthor = false;
      let startsSequence = true;
      let endsSequence = true;
      let showsentAt = true;

      if (previous) {
        let previousMoment = moment(previous.sentAt);
        let previousDuration = moment.duration(
          currentMoment.diff(previousMoment)
        );
        prevBySameAuthor = previous.from === current.from;

        if (prevBySameAuthor && previousDuration.as("hours") < 1) {
          startsSequence = false;
        }

        if (previousDuration.as("hours") < 1) {
          showsentAt = false;
        }
      }

      if (next) {
        let nextMoment = moment(next.sentAt);
        let nextDuration = moment.duration(nextMoment.diff(currentMoment));
        nextBySameAuthor = next.from === current.autfromhor;

        if (nextBySameAuthor && nextDuration.as("hours") < 1) {
          endsSequence = false;
        }
      }
      if (
        PATIENT_USER_ID === current.to ||
        PATIENT_USER_ID === current.from ||
        isMine
      ) {
        tempMessages.push(
          <Message
            key={i}
            isMine={isMine}
            startsSequence={startsSequence}
            endsSequence={endsSequence}
            showsentAt={showsentAt}
            data={current}
          />
        );
      }

      // Proceed to the next message.
      i += 1;
    }

    return tempMessages;
  };

  return (
    <div className="message-list">
      <div className="message-list-container">{renderMessages()}</div>
      <Compose PATIENT_ID={PATIENT_USER_ID} date={moment().format()} />
    </div>
  );
}
