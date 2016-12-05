clear
%% Loading data
DataPath = './DataExtraction/';
load([DataPath, 'DataMat.mat'])

%% Preprocessing 
% autoenc_ = trainAutoencoder(x_train',20, 'MaxEpochs',400,'ScaleData',false);
% 
% x_tr_encd = encode(autoenc,x_train')';
% x_tst_encd = encode(autoenc,x_tst')';
% 
% 
% %% Training and predicting
% prd_prop = zeros(size(x_tst, 1), 5);
% 
% for k = 1:5
% 
%     model  = fitrsvm(x_tr_encd, y_train(:, k));
% 
% %     x_tst_pca= bsxfun(@minus,x_tst,mu)*coeff;
% %     x_tst_pca = x_tst_pca(:, 1 : D);
%     
%     prd_prop(:, k) = predict(model, x_tst_encd);
%     
%     disp(['Done target ' num2str(k)])
% 
% end

autoenc1 = trainAutoencoder(x_train',20,...
    'L2WeightRegularization',0.001,...
    'SparsityRegularization',4,...
    'DecoderTransferFunction','purelin',...
    'ScaleData',false);

features1 = encode(autoenc1,x_train');

autoenc2 = trainAutoencoder(features1,20,...
    'L2WeightRegularization',0.001,...
    'SparsityRegularization',4,...
    'DecoderTransferFunction','purelin',...
    'ScaleData',false);
features2 = encode(autoenc2,features1);
%% Training and predicting
prd_prop = zeros(size(x_tst, 1), 5);

for k = 1:5
    
    softnet = trainSoftmaxLayer(features2, y_train(:, k)','LossFunction','crossentropy');
    
    deepnet = stack(autoenc1,autoenc2,softnet);
    
    deepnet = train(deepnet,x_train',y_train(:, k)');
    
    prd_prop(:, k) = deepnet(x_tst')';
end
%% Generating File
variables = {'PIDN', 'Ca', 'P', 'pH', 'SOC', 'Sand'};

data = table(tst_id, prd_prop(:,1), prd_prop(:,2), prd_prop(:,3), prd_prop(:,4), prd_prop(:,5), ...
    'VariableNames', variables);

writetable(data, './Results/autoen_softmax.csv')