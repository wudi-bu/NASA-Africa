clear

addpath ./2ndPlace/
DataPath = './DataExtraction/';
load([DataPath, 'DataMat.mat'])
load('netcell.mat')

[x_tst_1, x_tst_2] = prep(x_tst);

x_test   = x_tst_2;

prd_prop = zeros(size(x_test,1),5);
for k = 1:5 %loop over 5 targets

        if k==5 % target 5, Sand
                
                x_test  = x_tst_1;
                
        end
        
        %array for all 20 models results for target k
        y_target_all = zeros(size(x_test,1),20); 

        for m = 1:20                      %loop averaging 20 CV  

                y_target = x_test(:,1)*0; %init test results with 0

                for n = 1:5  % averaging 5 CV models
                        net = netcell{n, m, k};
                        
                        %apply the network to the test data and acc.
                        y_target = y_target + net(x_test')';
                end

                %average all 5 CV models
                y_target_all(:,m) = y_target/5;
                
        end

        disp(['Done target ' num2str(k)])

        prd_prop(:,k) = mean(y_target_all, 2); %average results
end

%% Generating File
variables = {'PIDN', 'Ca', 'P', 'pH', 'SOC', 'Sand'};

data = table(tst_id, prd_prop(:,1), prd_prop(:,2), prd_prop(:,3), prd_prop(:,4), prd_prop(:,5), ...
    'VariableNames', variables);

writetable(data, './Results/2nd.csv')