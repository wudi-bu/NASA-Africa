clear

DataPath = '/Users/litaochen/Desktop/EC601/NASAAFRICA/Code/data/';
load([DataPath 'DataMat.mat'])
load v.mat

% PCAthreshold = 0.99;
% 
% [coeff, x_pca, latent, ~, explained, mu] = pca(x_train);
% score = cumsum(latent) ./ sum(latent);
% D = find(score > PCAthreshold, 1 );
% x_pca = x_pca(:, 1 : D);
% 
% predict = zeros(size(x_tst, 1), 5);
% for k = 1:5
% 
%     model  = fitrsvm(x_pca, y_train(:, k));
% 
%     x_tst_pca= bsxfun(@minus,x_tst,mu)*coeff;
% 
%     x_tst_pca = x_tst_pca(:, 1 : D);
%     predict(:, k) = predict(model, x_tst_pca);
%     
%     disp(['Done target ' num2str(k)])
% 
% end
% 

variables = {'PIDN', 'Ca', 'P', 'pH', 'SOC', 'Sand'};

data = table(tst_id, v(:,1), v(:,2), v(:,3), v(:,4), v(:,5), 'VariableNames', variables);

writetable(data, 'tableversion.csv')