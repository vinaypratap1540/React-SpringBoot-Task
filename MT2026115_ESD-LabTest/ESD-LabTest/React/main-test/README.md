# Main Test — Library

Three small, separate React components for a library page. Each task is checked by
**one test** and is worth **3 marks** (9 in all):

| Task | What you build | File you edit | Its test | Marks |
| --- | --- | --- | --- | --- |
| 1 | A book card | `src/BookCard.jsx` | `test/1-book-card.test.jsx` | 3 |
| 2 | A list of titles | `src/BookList.jsx` | `test/2-book-list.test.jsx` | 3 |
| 3 | A like counter | `src/LikeButton.jsx` | `test/3-like-button.test.jsx` | 3 |

A task gets its marks when its test passes.

Everything here is from React Labs 1 and 2: components and props, conditional rendering,
lists and keys, `useState` and events. No other hooks or libraries are needed.

**No AI tools.** Type it yourself.

## Setup

You need **Node.js 20.19 or newer** (`node -v`).

```bash
cd main-test
npm install          # once
npm test             # runs all three tests — at first all three fail
npm run dev          # the page → http://localhost:5173 (all three tasks on one page)
```

If the folder already came with a `node_modules` folder (the offline copy), run
**`npm rebuild`** once instead of `npm install` — it needs no internet.

Run one task's test while you work on it:

```bash
npx vitest run test/1-book-card.test.jsx
```

On Windows, if PowerShell says "running scripts is disabled", use the **Command Prompt**
terminal instead (VS Code terminal → the `+` dropdown).

## Rules

- **Don't change the tests** (`test/`) or the given files: `src/main.jsx`, `src/App.jsx`,
  `src/books.js`. You may add CSS to `src/styles.css`.
- Keep the file names, the default exports and the props as they are: the tests import them.
- The three tasks don't depend on each other. Do them in any order.
- Exact text matters: the tests look for the words below.

A book looks like `{ id: 1, title: "Clean Code", author: "Robert C. Martin", available: true }`.

## Task 1 — a book card (`src/BookCard.jsx`) · 3 marks

`BookCard({ book })` renders **one `<article>`** containing:

- the title in an **`<h3>`**;
- **`by Robert C. Martin`**: the word `by`, a space, then the author;
- **`Available`** if `book.available` is true, otherwise **`Checked out`**.

## Task 2 — a list of titles (`src/BookList.jsx`) · 3 marks

`BookList({ books })`:

- a **`<ul aria-label="Books">`** with one `<li>` per book. The `<li>` holds only the title,
  in the order given, with a **`key`** (the book's id);
- if `books` is empty: **`<p>No books found.</p>`**, and no list.

## Task 3 — a like counter (`src/LikeButton.jsx`) · 3 marks

`LikeButton()` (no props) shows two buttons:

- **`Like (0)`**: each click adds one, so it shows `Like (1)`, `Like (2)` and so on;
- **`Reset`**: sets the count back to 0. It is **disabled while the count is 0**.

Each `LikeButton` on a page keeps **its own** count: two of them don't share one.

## Done means

`npm test` ends with **`Tests  3 passed (3)`** (all 9 marks), and the page works in the
browser. Your TA
will tell you how to hand it in.
