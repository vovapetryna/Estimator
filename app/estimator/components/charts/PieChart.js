'use strict';

import {Doughnut} from "@reactchartjs/react-chart.js";
import styles from './PieChart.module.css';

export default function PieChart(props) {
  const options = {
    aspectRatio: 1,
    maintainAspectRatio: false,
    legend: {
      display: false,
    },
  };

  const data = {
    labels: ['1', '2', '3', '4', '5', '6'],
    datasets: [
      {
        label: '# of Votes',
        data: [12, 19, 3],
        backgroundColor: [
          'rgba(255, 99, 132, 0.4)',
          'rgba(54, 162, 235, 0.4)',
          'rgba(255, 206, 86, 0.4)',
        ],
        borderColor: 'rgba(255, 255, 255, 1)',
        borderWidth: 2,
      },
    ],
  }

  return (
    <div className={styles.smallBox}>
      <Doughnut
        data={data}
        type={'doughnut'}
        height={null}
        width={null}
        options={options}
      />
    </div>
  );
}