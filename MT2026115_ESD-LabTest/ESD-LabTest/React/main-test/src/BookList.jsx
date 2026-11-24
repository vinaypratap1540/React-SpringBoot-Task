// TASK 2 · easy — every book's title in a list.
// Spec: README.md, task 2.   Test: npx vitest run test/2-book-list.test.jsx
//
// Props:  books = an array of books
export default function BookList({ books }) {
  // TODO: a <ul aria-label="Books"> with one <li> per title — or "No books found."
 
     if(books.length===0){
        return (<p>No books found.</p>);
     }

     return (
      <ul aria-label="Books">
        {books.map((book)=>(
          <li key={book.id}>
              {book.title}
          </li>
        ))}
      </ul>
     )
}
