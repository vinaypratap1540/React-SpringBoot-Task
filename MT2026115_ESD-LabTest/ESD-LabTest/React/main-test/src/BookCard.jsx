// TASK 1 · easy — one book as a card.
// Spec: README.md, task 1.   Test: npx vitest run test/1-book-card.test.jsx
//
// Props:  book = { id, title, author, available }
export default function BookCard({ book }) {
  // TODO: an <article> with the title in an <h3>, "by <author>", and "Available" or "Checked out".
  return (
   <article>
    <h3>{book.title}</h3>
    <p>by {book.author}</p>
    {book.available ? (<p>Available</p>) : (<p>Checked out</p>)}
    </article>
  );
}
