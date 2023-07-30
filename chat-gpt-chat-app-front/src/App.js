import Header from "./components/Header/Header";
import Screen from "./components/Screen/Screen";
import { DarkModeProvider } from './context/DarkModeContext';

export default function App() {
  return (
    <div>
      <DarkModeProvider>
        <Header />
        <Screen />
      </DarkModeProvider>
    </div>
  );
}


