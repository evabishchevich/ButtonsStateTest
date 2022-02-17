# ButtonsStateTest

A test project to play with FSM vs Redux state management.

There are 4 general states: Idle, Loading, Result, Error.  
Test buttons should be visible in Idle, Result and Error states.

Click on every button should paint a button in a different color. The other buttons should be
painted into a default color.

Going from Error/Result states to Idle should paint all button with a default color.
