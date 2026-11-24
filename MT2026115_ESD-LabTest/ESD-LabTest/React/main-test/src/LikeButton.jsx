// TASK 3 · easy — a like counter with a reset.
// Spec: README.md, task 3.   Test: npx vitest run test/3-like-button.test.jsx
//
// No props. Each LikeButton keeps its own count.
import { useState } from "react";

export default function LikeButton() {
  const [like, setLike] = useState(0)
  // TODO: a "Like (n)" button that counts clicks, and a "Reset" button.
  return(
  <div>
    <button onClick={()=>setLike(prev=>prev+1)}>Like ({like})</button>
    <button onClick={()=>setLike(0)} disabled={(like===0)}>Reset</button>
  </div>
  )
}
