// GIVEN — the books the page shows. Never change this array or its objects.
export const BOOKS = Object.freeze([
  { id: 1, title: "Clean Code", author: "Robert C. Martin", available: true },
  { id: 2, title: "The Pragmatic Programmer", author: "Andrew Hunt", available: true },
  { id: 3, title: "Refactoring", author: "Martin Fowler", available: false },
  { id: 4, title: "Head First Java", author: "Kathy Sierra", available: true },
  { id: 5, title: "The Mythical Man-Month", author: "Fred Brooks", available: true },
].map((book) => Object.freeze(book)));
