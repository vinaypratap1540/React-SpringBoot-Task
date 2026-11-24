// GIVEN — don't edit. It puts all three tasks on one page so you can see them in the browser.
import { BOOKS } from "./books.js";
import BookCard from "./BookCard.jsx";
import BookList from "./BookList.jsx";
import LikeButton from "./LikeButton.jsx";

export default function App() {
  return (
    <main>
      <h1>Library</h1>

      <section>
        <h2>1 · BookCard</h2>
        <div className="cards">
          <BookCard book={BOOKS[0]} />
          <BookCard book={BOOKS[2]} />
        </div>
      </section>

      <section>
        <h2>2 · BookList</h2>
        <BookList books={BOOKS} />
      </section>

      <section>
        <h2>3 · LikeButton</h2>
        <LikeButton />
      </section>
    </main>
  );
}
