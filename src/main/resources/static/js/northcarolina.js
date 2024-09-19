document.addEventListener('DOMContentLoaded', function() {
    const pollsterSelect = document.getElementById('idpollster');
    const ctx = document.getElementById('northCarolinaChart').getContext('2d');
    let northCarolinaChart;

   
    function populatePollsterDropdown() {
        fetch('/api/pollings')
            .then(response => response.json())
            .then(pollings => {
                const validTerritoryNames = new Set();
                pollings.forEach(polling => {
                    if (polling.territory.territory=='North Carolina') {  
                        validTerritoryNames.add(polling.pollster.idpollster);
                    }
                });

                return fetch('/api/pollsters')
                    .then(response => response.json())
                    .then(pollsters => {
                        const filteredPollsters = pollsters.filter(pollster =>
                            validTerritoryNames.has(pollster.idpollster)
                        );

                        const options = filteredPollsters.map(pollster =>
                            `<option value="${pollster.idpollster}">${pollster.pollster}</option>`
                        ).join('');
                        pollsterSelect.innerHTML = `<option value="">Select a Pollster</option>${options}`;

                        
                        initializeEmptyChart();
                    });
            });
    }

   
    function fetchAndUpdateChart(pollsterId) {
        fetch('/api/pollings')
            .then(response => response.json())
            .then(pollings => {
                const filteredPollings = pollings.filter(polling =>
                    polling.pollster.idpollster == pollsterId && polling.territory.territory=='North Carolina'
                );

                const candidatePollings = [1, 2, 3, 4].map(candidateId => {
                    const candidatePollings = filteredPollings.filter(polling => polling.candidate.idcandidate == candidateId);
                    return {
                        labels: candidatePollings.map(polling =>
                            `${polling.pollster.pollster} ${new Date(polling.poll_date).toLocaleDateString()}`
                        ),
                        data: candidatePollings.map(polling => polling.poll_result)
                    };
                });

                const allLabels = Array.from(new Set(candidatePollings.flatMap(cp => cp.labels)));

                const datasets = [
                    {
                        label: 'Kamala Harris',
                        data: allLabels.map(label => candidatePollings[0].labels.includes(label) ? candidatePollings[0].data[candidatePollings[0].labels.indexOf(label)] : 0),
                        borderColor: 'black',
                        backgroundColor: 'blue',
                        pointBackgroundColor: 'white',
                        pointBorderColor: 'blue',
                        borderWidth: 3,
                        pointRadius: 5,
                        pointHoverRadius: 10,
                        lineTension: 0.4,
                    },
                    {
                        label: 'Donald J. Trump',
                        data: allLabels.map(label => candidatePollings[1].labels.includes(label) ? candidatePollings[1].data[candidatePollings[1].labels.indexOf(label)] : 0),
                        borderColor: 'black',
                        backgroundColor: 'red',
                        pointBackgroundColor: 'white',
                        pointBorderColor: 'red',
                        borderWidth: 3,
                        pointRadius: 5,
                        pointHoverRadius: 10,
                        lineTension: 0.4,
                    },
                    {
                        label: 'Robert F. Kennedy Jr',
                        data: allLabels.map(label => candidatePollings[2].labels.includes(label) ? candidatePollings[2].data[candidatePollings[2].labels.indexOf(label)] : 0),
                        borderColor: 'black',
                        backgroundColor: 'green',
                        pointBackgroundColor: 'white',
                        pointBorderColor: 'green',
                        borderWidth: 3,
                        pointRadius: 5,
                        pointHoverRadius: 10,
                        lineTension: 0.4,
                    },
                    {
                        label: 'Others',
                        data: allLabels.map(label => candidatePollings[3].labels.includes(label) ? candidatePollings[3].data[candidatePollings[3].labels.indexOf(label)] : 0),
                        borderColor: 'black',
                        backgroundColor: 'fuchsia',
                        pointBackgroundColor: 'white',
                        pointBorderColor: 'fuchsia',
                        borderWidth: 3,
                        pointRadius: 5,
                        pointHoverRadius: 10,
                        lineTension: 0.4,
                    }
                ];

                if (northCarolinaChart) {
                    northCarolinaChart.destroy();
                }

                northCarolinaChart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: allLabels,
                        datasets: datasets
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: true,
                        plugins: {
                            legend: {
                                labels: {
                                    font: {
                                        size: 16,
                                        weight: 'bold'
                                    },
                                    color: 'blue'
                                }
                            },
                            tooltip: {
                                callbacks: {
                                    label: function(context) {
                                        return `${context.dataset.label || ''}: ${(context.parsed.y * 100).toFixed(0)}%`;
                                    }
                                }
                            }
                        },
                        scales: {
                            y: {
                                beginAtZero: true,
                                title: {
                                    display: true,
                                    text: 'RESULTS',
                                    font: {
                                        size: 18,
                                        weight: 'bold'
                                    }
                                },
                                ticks: {
                                    callback: function(value) {
                                        return `${(value * 100).toFixed(0)}%`;
                                    }
                                }
                            },
                            x: {
                                title: {
                                    display: true,
                                    text: 'TIME',
                                    font: {
                                        size: 18,
                                        weight: 'bold'
                                    }
                                },
                                ticks: {
                                    autoSkip: true,
                                    maxTicksLimit: 20
                                }
                            }
                        }
                    }
                });
            });
    }

    // Function to initialize an empty chart
    function initializeEmptyChart() {
        if (northCarolinaChart) {
            northCarolinaChart.destroy();
        }

        northCarolinaChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: [],
                datasets: [{
                    label: 'No pollster selected',
                    data: [],
                    borderColor: 'grey',
                    backgroundColor: 'lightgrey',
                    borderWidth: 3,
                    pointRadius: 5,
                    pointHoverRadius: 10,
                    lineTension: 0.4,
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        labels: {
                            font: {
                                size: 16,
                                weight: 'bold'
                            },
                            color: 'blue'
                        }
                    },
                    tooltip: {
                        callbacks: {
                            label: function(context) {
                                return `${context.dataset.label || ''}: No data available`;
                            }
                        }
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: 'RESULTS',
                            font: {
                                size: 18,
                                weight: 'bold'
                            }
                        },
                        ticks: {
                            callback: function(value) {
                                return `${(value * 100).toFixed(0)}%`;
                            }
                        }
                    },
                    x: {
                        title: {
                            display: true,
                            text: 'TIME',
                            font: {
                                size: 18,
                                weight: 'bold'
                            }
                        },
                        ticks: {
                            autoSkip: false,
                            maxTicksLimit: 20
                        }
                    }
                }
            }
        });
    }

    
    populatePollsterDropdown();

    pollsterSelect.addEventListener('change', function() {
        const selectedPollsterId = this.value;
        if (selectedPollsterId) {
            fetchAndUpdateChart(selectedPollsterId);
        } else {
            initializeEmptyChart(); 
        }
    });
});