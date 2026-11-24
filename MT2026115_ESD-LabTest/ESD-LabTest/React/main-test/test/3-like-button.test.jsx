// TASK 3 · easy — LikeButton (src/LikeButton.jsx). README.md, task 3.
import { test, expect } from "vitest";
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import LikeButton from "../src/LikeButton.jsx";

test("Task 3 (easy) · LikeButton counts clicks, Reset sets it back to 0, and each button keeps its own count", async () => {
  const user = userEvent.setup();
  const { unmount } = render(<LikeButton />);

  // Starts at 0, and Reset can't be used yet
  expect(screen.getByRole("button", { name: "Like (0)" })).toBeInTheDocument();
  expect(screen.getByRole("button", { name: "Reset" })).toBeDisabled();

  // Three likes
  await user.click(screen.getByRole("button", { name: "Like (0)" }));
  await user.click(screen.getByRole("button", { name: "Like (1)" }));
  await user.click(screen.getByRole("button", { name: "Like (2)" }));
  expect(screen.getByRole("button", { name: "Like (3)" })).toBeInTheDocument();
  expect(screen.getByRole("button", { name: "Reset" })).toBeEnabled();

  // Reset
  await user.click(screen.getByRole("button", { name: "Reset" }));
  expect(screen.getByRole("button", { name: "Like (0)" })).toBeInTheDocument();
  expect(screen.getByRole("button", { name: "Reset" })).toBeDisabled();
  unmount();

  // Two LikeButtons don't share a count
  render(
    <>
      <LikeButton />
      <LikeButton />
    </>
  );
  await user.click(screen.getAllByRole("button", { name: "Like (0)" })[0]);
  expect(screen.getByRole("button", { name: "Like (1)" })).toBeInTheDocument();
  expect(screen.getAllByRole("button", { name: "Like (0)" })).toHaveLength(1);
  await user.click(screen.getByRole("button", { name: "Like (0)" }));
  expect(screen.getAllByRole("button", { name: "Like (1)" })).toHaveLength(2);
});
