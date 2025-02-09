import { ValidationButton } from "../../components/ValidationButton.tsx";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { getTrainerCount } from "../../services/trainerService.ts";

export const IndexPage = () => {
  const navigate = useNavigate();
  const onClick = () => {
    navigate("planTrip");
  }

  const [seniorTrainerCount, setSeniorTrainerCount] = useState<number>();
  useEffect(() => {
    getTrainerCount().then(res => setSeniorTrainerCount(res.data))
  }, []);

  return (
    <div className="min-h-screen bg-slate-100 grid place-content-center">
      {seniorTrainerCount == undefined ?
        <p>Ładowanie...</p> :
        seniorTrainerCount === 0 && <p>Brak trenerów seniorów</p>}
      <ValidationButton enabled={!!seniorTrainerCount && seniorTrainerCount > 0} onClick={onClick} className="px-4">
        Start use case
      </ValidationButton>

    </div>
  );
};
