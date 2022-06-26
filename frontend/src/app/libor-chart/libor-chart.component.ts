import {HttpClient} from "@angular/common/http";
import { Component, OnInit } from "@angular/core";

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
  public values : any[][];

  constructor(private http : HttpClient) {
    this.initChartData();
  }
  ngOnInit(): void {
    this.http.get<any>('https://raw.githubusercontent.com/Cesarmosqueira/fimbi/master/ORB.json')
      .subscribe({
      next: (data) => {
        this.values = data.dataset.data;
        this.initChartData();
      },
      error: (e) => {
        console.error(e);
      }
    });

  }

  initChartData(): void {

    console.log(this.values);
    let dates : any[] = [];

    for(let d in this.values) {
      let time = new Date(d[0]).getTime();
      dates.push([time, d[1]])
    }

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

