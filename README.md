# Game App

The app was developed in :
- Kotlin
- MVVM Architectural pattern
- Hilt
- Coroutines
- Room
- Mockito for unit testing
- Android Min SDK: 24
- Android Target SDK: 33

## Project Structure
Globally the project has the following top level packages:
1. **data**: Basically data layer that contains data source both local or remote, and all the data accessing and manipulating components.
2. **di**: Dependency providing classes using Dagger-Hilt.
3. **ui**: Basically presenter layer that contains the classes View (Activity, Fragment), Adapter, ViewModel.
4. **domain**: Basically domain layer that contains all business logic.
5. **utils**: Contains Utility & Helper classes.

### UI Layer packages
Features/screen is placed in `ui` package. Here is the list of app feature packages:
- base ⇒ package that contains base class for fragment and activity.
- bottomsheet ⇒ package that contains bottom sheet dialog.
- detail ⇒ package that contains detail screen for selected game.
- favorite ⇒ package that contains list of game favorite screen.
- games ⇒ package that contains list of game screen.
- home ⇒ package that contains home screen that used to parent for games and favorite screen.

## Navigation Graph
- `nav_graph` : Nav graph used for main application flow.

## Technical Debts
- need bump up coverage test for all class

## Credits
- **Dimas Arya Murdiyan** - dimasaryamurdiyan123@gmail.com