// TASK 1 · easy — BookCard (src/BookCard.jsx). README.md, task 1.
import { test, expect } from "vitest";
import { render, screen } from "@testing-library/react";
import BookCard from "../src/BookCard.jsx";
import { BOOKS } from "./book-data.js";

test("Task 1 (easy) · BookCard shows the title, the author, and whether the book is available", () => {
  // An available book
  const { unmount } = render(<BookCard book={BOOKS[0]} />);
  const card = screen.getByRole("article");
  expect(screen.getByRole("heading", { level: 3, name: "Clean Code" })).toBeInTheDocument();
  expect(card).toHaveTextContent("by Robert C. Martin");
  expect(screen.getByText("Available")).toBeInTheDocument();
  expect(screen.queryByText("Checked out")).not.toBeInTheDocument();
  unmount();

  // A checked-out book
  render(<BookCard book={BOOKS[2]} />);
  expect(screen.getByRole("heading", { level: 3, name: "Refactoring" })).toBeInTheDocument();
  expect(screen.getByRole("article")).toHaveTextContent("by Martin Fowler");
  expect(screen.getByText("Checked out")).toBeInTheDocument();
  expect(screen.queryByText("Available")).not.toBeInTheDocument();
});
