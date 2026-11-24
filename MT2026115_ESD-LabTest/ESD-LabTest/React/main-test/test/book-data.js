// Test data, frozen all the way down: a component that changes it fails loudly.
const deepFreeze = (value) => {
  if (value && typeof value === "object") {
    Object.values(value).forEach(deepFreeze);
    Object.freeze(value);
  }
  return value;
};

export const BOOKS = deepFreeze([
  { id: 1, title: "Clean Code", author: "Robert C. Martin", available: true },
  { id: 2, title: "The Pragmatic Programmer", author: "Andrew Hunt", available: true },
  { id: 3, title: "Refactoring", author: "Martin Fowler", available: false },
  { id: 4, title: "Head First Java", author: "Kathy Sierra", available: true },
  { id: 5, title: "The Mythical Man-Month", author: "Fred Brooks", available: true },
]);

export const TITLES = BOOKS.map((b) => b.title);
