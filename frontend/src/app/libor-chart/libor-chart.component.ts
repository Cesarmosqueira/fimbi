import {HttpClient} from "@angular/common/http";
import { Component, Input, OnInit } from "@angular/core";

import {
  ApexAxisChartSeries,
  ApexChart,
  ApexTitleSubtitle,
  ApexDataLabels,
  ApexFill,
  ApexMarkers,
  ApexYAxis,
  ApexXAxis,
  ApexTooltip
} from "ng-apexcharts";


@Component({
  selector: '[app-libor-chart]',
  templateUrl: './libor-chart.component.html',
  styleUrls: ['./libor-chart.component.css']
})
export class LiborChartComponent implements OnInit {
  public series: ApexAxisChartSeries;
  public chart: ApexChart;
  public dataLabels: ApexDataLabels;
  public markers: ApexMarkers;
  public title: ApexTitleSubtitle;
  public fill: ApexFill;
  public yaxis: ApexYAxis;
  public xaxis: ApexXAxis;
  public tooltip: ApexTooltip;

  @Input('app-libor-chart') values : any[][];
  constructor() {
  }
  ngOnInit(): void {
    this.initChartData();
  }

  initChartData(): void {
    let dates : any[] = [];

    for(let i=0; i< this.values.length; i++) {
      let x = new Date(this.values[i][0]).getTime()
      let y = this.values[i][1]
      dates.push([x, y])
    }
    console.log(dates);

    this.series = [
      {
        name: "LIBOR",
        data: dates
      }
    ];
    this.chart = {
      type: "area",
      stacked: false,
      height: 350,
      zoom: {
        type: "x",
        enabled: true,
        autoScaleYaxis: true
      },
      toolbar: {
        autoSelected: "zoom"
      }
    };
    this.dataLabels = {
      enabled: false
    };
    this.markers = {
      size: 0
    };
    this.title = {
      text: "LIBOR",
      align: "left"
    };
    this.fill = {
      type: "gradient",
      gradient: {
        shadeIntensity: 1,
        inverseColors: false,
        opacityFrom: 0.5,
        opacityTo: 0,
        stops: [0, 90, 100]
      }
    };
    this.yaxis = {
      labels: {
        formatter: function(val : number) {
          return (val / 1).toFixed(3);
        }
      },
      title: {
        text: "Value"
      }
    };
    this.xaxis = {
      type: "datetime"
    };
    this.tooltip = {
      shared: false,
      y: {
        formatter: function(val : number) {
          return (val / 1).toFixed(5);
        }
      }
    };
  }
}

