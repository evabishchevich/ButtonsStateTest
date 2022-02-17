# ButtonsStateTest

A test project to play with different approaches of state management.

There are 4 general states: `Idle`, `Loading`, `Result`, `Error`.

### Requirements

1. Test buttons should be visible in `Idle`, `Result` and `Error` states.
2. Click on a button should change a color of this button. The other buttons should be painted into
   a default color.
3. Going from `Error`/`Result` states to `Idle` should paint all buttons with a default color.
