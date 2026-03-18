import React, { useEffect, useState } from 'react'

function Time() {
    const [time, setTime] = useState("기본값");
    useEffect(() => {
      fetch("http://localhost:8080/hw01/worldtime/json")
        .then(resp=>resp.json())
        .then(({now})=>setTime(now))
    }, [])
    
  return (
    <h1>{time}</h1>
  )
}

export default Time