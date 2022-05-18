# Exploratory Testing Charter

| Tester      | Jankó Júlia |
| ----------- | ----------- |
| Purpose      | Find bugs and issues in the gameplay.       |
| Test Start   | 2022.05.16. 11:00    |
| Timebox | Normal (60-90 min)|

## Classify the bugs

### Common Game bugs
1. Freezing, Crashing
2. Graphics Glitch
3. Gameplay Defect
4. In-game Purchase
5. Audio Problem
6. Text problem
7. Save Glitch
> In-game Purchase and Audio Problem is irrelevant in our case.
## Test Ideas
### 1. Freezing, Crashing
- Try to do actions we shouldn't be able to
- Overwhelm the game with commands
### 2. Graphics Glitch
- Play multiple different scenarios that causes the graphics to change e.g. placing a teleport, moving with settlers 
### 3. Gameplay Defect
- Play multiple different scenarios
### 6. Text Problem
- Try to get every scenario with text response from the game
### 7. Save Glitch
- Save and load game in different scenarios
## Logs 
Logs can be viewed [here](exploratory_logs/log.md)
## Reporting Summary
| Action 							| Time		|
| --------------------------------- | --------- |
| Test Design and Execution 		| 60%       |
| Issue Investigation & Reporting 	| 15%       |
| Bug Review & Reporting			| 15%       |
| Session Set-up					| 10%       |

### Test Design and Execution - 60%
- 35% actual testing
- 25% test Set-up, making load files, documentation

### Issue Investigation & Reporting - 15%
- 5% making pictures
- 10% documentation

### Bug Review & Reporting - 15%
- 5% making pictures
- 10% documentation

### Session Set-up - 10%
- documentation
- small changes in code to help testing

## Conclusion
Found 2 bugs and 2 issues that should be solved.