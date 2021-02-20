'use strict';

import {HorizontalBar} from "@reactchartjs/react-chart.js";
import styles from './BarChart.module.css';

export default function BarChart(props) {
  const options = {
    tooltips: {enabled: false},
    hover: {mode: null},
    aspectRatio: 1.2,
    scales: {
      yAxes: [
        {
          stacked: true,
          ticks: {
            beginAtZero: true,
          },
          display: false,
        },
      ],
      xAxes: [
        {
          stacked: true,
          display: false,
        }
      ]
    },
    legend: {
      display: false,
    },
  };

  const data = {
    labels: ['1', '2', '3', '4'],
    datasets: [
      {
        label: '# of Votes 1',
        data: [12],
        backgroundColor: 'rgba(255, 99, 132, 0.4)',
        borderColor: 'rgba(255, 255, 255, 1)',
        borderWidth: 2,
      },
      {
        label: '# of Votes 2',
        data: [19],
        backgroundColor: 'rgba(54, 162, 235, 0.4)',
        borderColor: 'rgba(255, 255, 255, 1)',
        borderWidth: 2,
      },
      {
        label: '# of Votes 3',
        data: [3],
        backgroundColor: 'rgba(255, 206, 86, 0.4)',
        borderColor: 'rgba(255, 255, 255, 1)',
        borderWidth: 2,
      },
    ],
  }

  return (
    <div className={styles.longBox}>
    <HorizontalBar
      type={'horizontalBar'}
      data={data}
      height={null}
      width={null}
      options={options}/>
    </div>
  );
}