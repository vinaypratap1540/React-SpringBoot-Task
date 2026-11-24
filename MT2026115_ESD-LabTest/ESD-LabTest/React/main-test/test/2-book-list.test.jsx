// TASK 2 · easy — BookList (src/BookList.jsx). README.md, task 2.
import { test, expect, vi } from "vitest";
import { render, screen, within } from "@testing-library/react";
import BookList from "../src/BookList.jsx";
import { BOOKS, TITLES } from "./book-data.js";

test("Task 2 (easy) · BookList lists every title, in order, each with a key — or 'No books found.'", () => {
  const errors = vi.spyOn(console, "error").mockImplementation(() => {});

  const { unmount } = render(<BookList books={BOOKS} />);
  const list = screen.getByRole("list", { name: "Books" });
  const items = within(list).getAllByRole("listitem");
  expect(items.map((li) => li.textContent)).toEqual(TITLES);

  // React warns when list items have no key
  const warnings = errors.mock.calls.map((args) => args.map(String).join(" ")).join("\n");
  expect(warnings).not.toMatch(/unique "key"/);
  unmount();

  // No books
  render(<BookList books={[]} />);
  expect(screen.getByText("No books found.")).toBeInTheDocument();
  expect(screen.queryByRole("list")).not.toBeInTheDocument();
});
