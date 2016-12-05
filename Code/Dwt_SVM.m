clear
%% Loading data
DataPath = './DataExtraction/';
load([DataPath, 'DataMat.mat'])

%% Preprocessing ()
PCAthreshold = 0.99;

[coeff, x_pca, latent, ~, explained, mu] = pca(x_train);
score = cumsum(latent) ./ sum(latent);
D = find(score > PCAthreshold, 1 );
x_pca = x_pca(:, 1 : D);

%% Training and predicting
prd_prop = zeros(size(x_tst, 1), 5);

for k = 1:5

    model  = fitrsvm(x_pca, y_train(:, k));

    x_tst_pca= bsxfun(@minus,x_tst,mu)*coeff;
    x_tst_pca = x_tst_pca(:, 1 : D);
    
    prd_prop(:, k) = predict(model, x_tst_pca);
    
    disp(['Done target ' num2str(k)])

end

%% Generating File
variables = {'PIDN', 'Ca', 'P', 'pH', 'SOC', 'Sand'};

data = table(tst_id, prd_prop(:,1), prd_prop(:,2), prd_prop(:,3), prd_prop(:,4), prd_prop(:,5), ...
    'VariableNames', variables);

writetable(data, './Results/tableversion.csv')