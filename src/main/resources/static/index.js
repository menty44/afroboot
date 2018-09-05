/**
 * Created by admin on 7/9/18.
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

/**
 * Created by admin on 6/3/18.
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */
// mainApp.controller('testController', function($scope, $http) {
//     $scope.message = "This page will be reverse";
//     //$scope.name = "";
//     // $scope.reverseme = function reverseme() {
//     //     var rv = $scope.name;
//     //     console.log(rv)
//     //     $http({
//     //         method : "GET",
//     //         url : "http://localhost:8080/numbers"
//     //     }).then(function mySuccess(response) {
//     //         var test = response.data;
//     //         console.log(test);
//     //         $scope.succ = test;
//     //     }, function myError(response) {
//     //         var test = response.data;
//     //         console.log(test);
//     //         $scope.err = response.response;
//     //     });
//     // }
// });

// var mainApp = angular.module('mainApp', ['ng-fusioncharts']);
// angular.module('mainApp').controller('operatorController', ['$rootScope', '$scope', function ($scope, $http) {

mainApp.controller('mainController', function($scope, $http, $location) {


    $scope.reverseme = function reverseme() {
        var rv = $scope.name;
        console.log(rv);
        $http({
            method : "GET",
            url : "http://localhost:8080/numbers"
        }).then(function mySuccess(response) {
            var test = response.data;
            console.log(test);
            $scope.succ = test;
        }, function myError(response) {
            var test = response.data;
            console.log(test);
            $scope.err = response.response;
        });
    };

    $scope.duration = function duration() {

        var rv = $scope.name;
        console.log(rv);
        $http({
            method : "GET",
            url : "http://localhost:8080/numbers"
        }).then(function mySuccess(response) {
            var test = response.data;

            console.log('operator',test);

            $scope.myDataSource = {
                chart: {
                    caption: "CALLS ACCORDING TO TELCOS",
                    // subcaption: "Last Year",
                    startingangle: "120",
                    showlabels: "0",
                    showlegend: "1",
                    enablemultislicing: "0",
                    slicingdistance: "15",
                    showpercentvalues: "1",
                    showpercentintooltip: "0",
                    plottooltext: "Network : $label Total calls : $datavalue",
                    theme: "fint"
                },
                data: [
                    {
                        label: "YU",
                        value: "1250400"
                    },
                    {
                        label: "AIRTEL",
                        value: "1463300"
                    },
                    {
                        label: "SAFARICOM",
                        value: "1050700"
                    }
                ]
            };

            Array.prototype.sum = function (prop) {
                var total = 0;
                for ( var i = 0, _len = this.length; i < _len; i++ ) {
                    total += this[i][prop]
                }
                return total
            };

            // function sum(items, prop){
            //     return items.reduce( function(a, b){
            //         return a + b[prop];
            //     }, 0);
            // };

            //traveler.sum("Amount");
            var t = test.sum('duration');

            localStorage.setItem('totalduration', JSON.stringify(t));

            console.log('total duration',t );

            var text = "";

            var i;

            for (i = 0; i < test.length; i++) {
                text += test[i] ;

                console.log('the text', JSON.stringify(text));
            }

            angular.forEach(response, function(value, key) {
                //this.push(key + ': ' + value);
                if(value.name == 'Yu'){
                    console.log('values' + JSON.parse(value.duration));
                    console.log('valuesdata' + JSON.parse(response));

                }
            });



            $scope.succ = test;
        }, function myError(response) {
            var test = response.data;
            console.log(test);
            $scope.err = response.response;
        });
    };

    $scope.check = function check(num) {

        console.log('Passed Number', num);
    };

    $scope.onausers = function onausers() {

        console.log('users have been activated');

        $http({
            method : "GET",
            url : "https://api.ona.io/api/v1/users?v=" + Date.now()
        }).then(function mySuccess(response) {

            var test = response.data;

            // $scope.onadata =  test;

            console.log('onausers', test);

            $scope.curPage = 1;
            $scope.itemsPerPage = 3;
            $scope.maxSize = 5;

            // this.onadata = test;
            $scope.onadata =  test;


            $scope.numOfPages = function () {
                return Math.ceil(test.length / $scope.itemsPerPage);

            };

            $scope.$watch('curPage + numPerPage', function() {
                var begin = (($scope.curPage - 1) * $scope.itemsPerPage),
                    end = begin + $scope.itemsPerPage;

                $scope.filteredItems = test.slice(begin, end);
            });


            // angular.forEach(test, function(value, key) {
            //     console.log('my values',value);
            //
            //     $scope.onadata = value;
            //     // angular.forEach(second, function(value, key) {
            //     //
            //     //     console.log('my values second',value);
            //     // });
            //
            // });

        })
    };

    $scope.onausersfetch = function onausersfetch(user) {

        console.log('my search res',user);

        $http({
            method : "GET",
            url : "https://api.ona.io/api/v1/users/"+user+"?v=" + Date.now()
        }).then(function mySuccess(response) {

            var test = response.data;
            $scope.search = test;
            console.log('my search res',test);

        });
    };



    $scope.reloaduse = function reloaduse() {
        $location.path('/another-route');
    }


    });

