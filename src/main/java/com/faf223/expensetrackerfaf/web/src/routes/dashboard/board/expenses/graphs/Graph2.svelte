<script>
	import Chart from 'chart.js/auto';
	import {onMount} from 'svelte';
	import {expenseData} from "../../../stores.js";
	import {globalStyles} from "../../../styles.js";

	let ctx;
	let chartCanvas;
	let chart = null;

	function groupAndSumByCategory() {
		const groupedData = new Map();
		$expenseData.forEach(expense => {
					const category = expense.expenseCategory.name;
					if (groupedData.has(category)) {
						groupedData.set(category, groupedData.get(category) + parseInt(expense.amount));
					} else {
						groupedData.set(category, parseInt(expense.amount));
					}
				}
		);

		return new Map([...groupedData.entries()].sort());
	}

	function createGraph() {
		try {
			const groupedExpenseData = groupAndSumByCategory();
			console.log("============= here start")
			console.log(groupedExpenseData);

			const chartLabels = [];
			const chartValues = [];

			for (const [label, value] of groupedExpenseData.entries()) {
				chartLabels.push(label);
				chartValues.push(value);
			}
			console.log(chartLabels)
			console.log(chartValues)
			console.log("============= here end")
			ctx = chartCanvas.getContext('2d');

			if (!chart) {
				chart = new Chart(ctx, {
					type: 'bar',
					data: {
						labels: chartLabels,
						datasets: [{
							label: 'Spendings',
							backgroundColor: [
								'rgb(107, 80, 107)',
								'rgb(171, 61, 169)',
								'rgb(222, 37, 218)',
								'rgb(235, 68, 232)',
								'rgb(255, 128, 255)'],
							data: chartValues
						}]
					},
					options: {
						responsive: true,
						maintainAspectRatio: false,
						plugins: {
							legend: {
								labels: {
									font: {
										weight: 'bold'
									},
									color: '#fff'
								}
							}
						}
					}
				});
			} else {
				chart.data.labels = chartLabels;
				chart.data.datasets[0].data = chartValues;
				chart.update();
			}
		} catch (error) {
			console.error('Error:', error);
		}
	}

	$: {
		if ($expenseData) {
			createGraph();
		}
	}

	onMount(() => {
		createGraph();
	});
</script>

<div id="chart" style="background-color: {$globalStyles.mainColor}">
	<canvas bind:this={chartCanvas}></canvas>
</div>

<style>
	#chart {
		box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
		transition: all 0.3s cubic-bezier(.25,.8,.25,1);
		flex: 1;
		border-radius: 0 0 10px 10px;
		margin: 0 0 10px 10px;
		min-width: 0;
		min-height:0;
	}

	#chart:hover {
		box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
	}
</style>
